<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="检修区域" prop="inspectionArea">
        <el-select v-model="queryParams.inspectionArea" placeholder="请选择检修区域" clearable>
          <el-option
              v-for="dict in dict.type.all_area"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检修状态" prop="maintenanceCondition">
        <el-select v-model="queryParams.maintenanceCondition" placeholder="请选择检修状态" clearable>
          <el-option
              v-for="dict in dict.type.prerepair_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检修总天数" prop="totalDays">
        <el-input
            v-model="queryParams.totalDays"
            placeholder="请输入检修总天数"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上次检修时间" prop="lastInspectionTime">
        <el-date-picker clearable
                        v-model="queryParams.lastInspectionTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择上次检修时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="检修备注" prop="inspectionRemarks">
        <el-input
            v-model="queryParams.inspectionRemarks"
            placeholder="请输入检修备注"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检修人" prop="maintenanceMan">
        <el-input
            v-model="queryParams.maintenanceMan"
            placeholder="请输入检修人"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检修问题未解决" prop="problem">
        <el-select v-model="queryParams.problem" placeholder="请选择检修问题未解决" clearable>
          <el-option
              v-for="dict in dict.type.prerepair_problem"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检修周" prop="weeks">
        <el-input
            v-model="queryParams.weeks"
            placeholder="请输入检修周"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <div>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </div>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:prerepair:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:prerepair:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:prerepair:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:prerepair:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="prerepairList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" type="index" align="center">
      </el-table-column>
      <el-table-column label="检修区域" align="center" prop="inspectionArea">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.all_area" :value="scope.row.inspectionArea"/>
        </template>
      </el-table-column>
      <el-table-column label="检修内容" align="center" prop="maintenanceContent"/>
      <el-table-column label="检修状态" align="center" prop="maintenanceCondition">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.prerepair_status" :value="scope.row.maintenanceCondition"/>
        </template>
      </el-table-column>
      <el-table-column label="检修总天数" align="center" prop="totalDays"/>
      <el-table-column label="上次检修时间" align="center" prop="lastInspectionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastInspectionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检修备注" align="center" prop="inspectionRemarks"/>
      <el-table-column label="检修人" align="center" prop="maintenanceMan"/>
      <el-table-column label="检修问题未解决" align="center" prop="problem">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.prerepair_problem" :value="scope.row.problem"/>
        </template>
      </el-table-column>
      <el-table-column label="检修周" align="center" prop="weeks"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:prerepair:edit']"
          >修改
          </el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:prerepair:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改预检修对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="检修区域" prop="inspectionArea">
          <el-select v-model="form.inspectionArea" placeholder="请选择检修区域">
            <el-option
                v-for="dict in dict.type.all_area"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="检修内容" style="width: 74%;">
          <el-input v-model="form.maintenanceContent"/>
        </el-form-item>
        <el-form-item label="检修状态" prop="maintenanceCondition">
          <el-select v-model="form.maintenanceCondition" placeholder="请选择检修状态">
            <el-option
                v-for="dict in dict.type.prerepair_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="检修总天数" prop="totalDays" style="width: 74%;">
          <el-input v-model="form.totalDays" placeholder="请输入检修总天数"/>
        </el-form-item>
        <el-form-item label="上次检修时间" prop="lastInspectionTime">
          <el-date-picker clearable
                          v-model="form.lastInspectionTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择上次检修时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检修备注" prop="inspectionRemarks" style="width: 74%;">
          <el-input v-model="form.inspectionRemarks" placeholder="请输入检修备注"/>
        </el-form-item>
        <el-form-item label="检修人" prop="maintenanceMan" style="width: 74%;">
          <el-input v-model="form.maintenanceMan" placeholder="请输入检修人"/>
        </el-form-item>
        <el-form-item label="检修问题未解决" prop="problem">
          <el-select v-model="form.problem" placeholder="请选择检修问题未解决">
            <el-option
                v-for="dict in dict.type.prerepair_problem"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="删除标志" prop="delFlag">-->
        <!--          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />-->
        <!--        </el-form-item>-->
        <el-form-item label="检修周" prop="weeks" style="width: 74%;">
          <el-input v-model="form.weeks" placeholder="请输入检修周"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
	import { listPrerepair, getPrerepair, delPrerepair, addPrerepair, updatePrerepair } from '@/api/pererpair/prerepair'

	export default {
		name: 'Prerepair',
		dicts: ['all_area', 'prerepair_status', 'prerepair_problem'],
		data () {
			return {
				// 遮罩层
				loading: true,
				// 选中数组
				ids: [],
				// 非单个禁用
				single: true,
				// 非多个禁用
				multiple: true,
				// 显示搜索条件
				showSearch: true,
				// 总条数
				total: 0,
				// 预检修表格数据
				prerepairList: [],
				// 弹出层标题
				title: '',
				// 是否显示弹出层
				open: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					inspectionArea: null,
					maintenanceContent: null,
					maintenanceCondition: null,
					totalDays: null,
					lastInspectionTime: null,
					inspectionRemarks: null,
					maintenanceMan: null,
					problem: null,
					weeks: null
				},
				// 表单参数
				form: {},
				// 表单校验
				rules: {
					inspectionArea: [
						{ required: true, message: '检修区域不能为空', trigger: 'change' }
					],
					maintenanceContent: [
						{ required: true, message: '检修内容不能为空', trigger: 'blur' }
					],
					maintenanceCondition: [
						{ required: true, message: '检修状态不能为空', trigger: 'change' }
					],
					totalDays: [
						{ required: true, message: '检修总天数不能为空', trigger: 'blur' }
					],
					delFlag: [
						{ required: true, message: '删除标志不能为空', trigger: 'blur' }
					],
					weeks: [
						{ required: true, message: '检修周不能为空', trigger: 'blur' }
					]
				}
			}
		},
		created () {
			this.getList()
		},
		methods: {
			/** 查询预检修列表 */
			getList () {
				this.loading = true
				listPrerepair(this.queryParams).then(response => {
					this.prerepairList = response.rows
					this.total = response.total
					this.loading = false
				})
			},
			// 取消按钮
			cancel () {
				this.open = false
				this.reset()
			},
			// 表单重置
			reset () {
				this.form = {
					overhaulId: null,
					inspectionArea: null,
					maintenanceContent: null,
					maintenanceCondition: null,
					totalDays: null,
					lastInspectionTime: null,
					inspectionRemarks: null,
					maintenanceMan: null,
					problem: null,
					delFlag: null,
					createBy: null,
					createTime: null,
					updateBy: null,
					updateTime: null,
					weeks: null
				}
				this.resetForm('form')
			},
			/** 搜索按钮操作 */
			handleQuery () {
				this.queryParams.pageNum = 1
				this.getList()
			},
			/** 重置按钮操作 */
			resetQuery () {
				this.resetForm('queryForm')
				this.handleQuery()
			},
			// 多选框选中数据
			handleSelectionChange (selection) {
				this.ids = selection.map(item => item.overhaulId)
				this.single = selection.length !== 1
				this.multiple = !selection.length
			},
			/** 新增按钮操作 */
			handleAdd () {
				this.reset()
				this.open = true
				this.title = '添加预检修'
			},
			/** 修改按钮操作 */
			handleUpdate (row) {
				this.reset()
				const overhaulId = row.overhaulId || this.ids
				getPrerepair(overhaulId).then(response => {
					this.form = response.data
					this.open = true
					this.title = '修改预检修'
				})
			},
			/** 提交按钮 */
			submitForm () {
				this.$refs['form'].validate(valid => {
					if (valid) {
						if (this.form.overhaulId != null) {
							updatePrerepair(this.form).then(response => {
								this.$modal.msgSuccess('修改成功')
								this.open = false
								this.getList()
							})
						} else {
							addPrerepair(this.form).then(response => {
								this.$modal.msgSuccess('新增成功')
								this.open = false
								this.getList()
							})
						}
					}
				})
			},
			/** 删除按钮操作 */
			handleDelete (row) {
				const overhaulIds = row.overhaulId || this.ids
				this.$modal.confirm('是否确认删除预检修编号为"' + overhaulIds + '"的数据项？').then(function () {
					return delPrerepair(overhaulIds)
				}).then(() => {
					this.getList()
					this.$modal.msgSuccess('删除成功')
				}).catch(() => {})
			},
			/** 导出按钮操作 */
			handleExport () {
				this.download('system/prerepair/export', {
					...this.queryParams
				}, `prerepair_${new Date().getTime()}.xlsx`)
			}
		}
	}
</script>
