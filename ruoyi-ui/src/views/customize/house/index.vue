<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="预警数量" prop="warnings">-->
<!--        <el-input-->
<!--            v-model="queryParams.warnings"-->
<!--            placeholder="请输入预警数量"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="库位" prop="storageLocation">
        <el-input
            v-model="queryParams.storageLocation"
            placeholder="请输入库位"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料名称" prop="materialName">
        <el-input
            v-model="queryParams.materialName"
            placeholder="请输入物料名称"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料编号" prop="materialNumber">
        <el-input
            v-model="queryParams.materialNumber"
            placeholder="请输入物料编号"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="规格型号" prop="specificationModel">-->
<!--        <el-input-->
<!--            v-model="queryParams.specificationModel"-->
<!--            placeholder="请输入规格型号"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="品牌" prop="brand">
        <el-input
            v-model="queryParams.brand"
            placeholder="请输入品牌"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="单位" prop="unit">-->
<!--        <el-select v-model="queryParams.unit" placeholder="请选择单位" clearable>-->
<!--          <el-option-->
<!--              v-for="dict in dict.type.unit"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="盘点数量" prop="countQuantity">-->
<!--        <el-input-->
<!--            v-model="queryParams.countQuantity"-->
<!--            placeholder="请输入盘点数量"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="库位说明" prop="storageLocationDescription">-->
<!--        <el-input-->
<!--            v-model="queryParams.storageLocationDescription"-->
<!--            placeholder="请输入库位说明"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="安全库存" prop="safetyStock">-->
<!--        <el-input-->
<!--            v-model="queryParams.safetyStock"-->
<!--            placeholder="请输入安全库存"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="货架" prop="shelf">
        <el-input
            v-model="queryParams.shelf"
            placeholder="请输入货架"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货架计数" prop="shelfCount">
        <el-input
            v-model="queryParams.shelfCount"
            placeholder="请输入货架计数"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="是否导入" prop="importNot">-->
<!--        <el-input-->
<!--            v-model="queryParams.importNot"-->
<!--            placeholder="请输入是否导入"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="单价" prop="price">-->
<!--        <el-input-->
<!--            v-model="queryParams.price"-->
<!--            placeholder="请输入单价"-->
<!--            clearable-->
<!--            @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
            v-hasPermi="['cus:house:add']"
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
            v-hasPermi="['cus:house:edit']"
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
            v-hasPermi="['cus:house:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="info"
            plain
            icon="el-icon-upload2"
            size="mini"
            @click="handleImport"
            v-hasPermi="['cus:house:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['cus:house:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="houseList" @selection-change="handleSelectionChange">
      <el-table-column fixed="left" type="selection" width="50px" align="center"/>
<!--      <el-table-column fixed="left" label="id" align="center" prop="id"/>-->
      <el-table-column fixed="left" label="库位" align="center"  width="150" prop="storageLocation"/>
      <el-table-column fixed="left" label="物料名称" align="center"  width="200" prop="materialName"/>
      <el-table-column fixed="left" label="物料编号" align="center" width="200" prop="materialNumber"/>
      <el-table-column label="规格型号" align="center" width="250" prop="specificationModel"/>
      <el-table-column label="品牌" align="center" prop="brand"/>
      <el-table-column label="单位" align="center" prop="unit">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.unit" :value="scope.row.unit"/>
        </template>
      </el-table-column>
      <el-table-column label="预警数量" align="center" prop="warnings"/>
      <el-table-column label="盘点数量" align="center" prop="countQuantity"/>
      <el-table-column label="随机S/旧件J/新件X" align="center" prop="status"/>
      <el-table-column label="库位说明" align="center" prop="storageLocationDescription"/>
      <el-table-column label="安全库存" align="center" prop="safetyStock"/>
      <el-table-column label="货架" align="center" prop="shelf"/>
      <el-table-column label="货架计数" align="center" prop="shelfCount"/>
      <el-table-column label="是否导入" align="center" prop="importNot"/>
      <el-table-column label="单价" align="center" prop="price"/>
      <el-table-column label="更新者" align="center" prop="updateBy"/>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['cus:house:edit']"
          >修改
          </el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['cus:house:remove']"
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

    <!-- 添加或修改库房备件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="预警数量" prop="warnings">
          <el-input v-model="form.warnings" placeholder="请输入预警数量"/>
        </el-form-item>
        <el-form-item label="库位" prop="storageLocation">
          <el-input v-model="form.storageLocation" placeholder="请输入库位"/>
        </el-form-item>
        <el-form-item label="物料名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物料名称"/>
        </el-form-item>
        <el-form-item label="物料编号" prop="materialNumber">
          <el-input v-model="form.materialNumber" placeholder="请输入物料编号"/>
        </el-form-item>
        <el-form-item label="规格型号" prop="specificationModel">
          <el-input v-model="form.specificationModel" placeholder="请输入规格型号"/>
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌"/>
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-select v-model="form.unit" placeholder="请选择单位">
            <el-option
                v-for="dict in dict.type.unit"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="盘点数量" prop="countQuantity">
          <el-input v-model="form.countQuantity" placeholder="请输入盘点数量"/>
        </el-form-item>
        <el-form-item label="库位说明" prop="storageLocationDescription">
          <el-input v-model="form.storageLocationDescription" placeholder="请输入库位说明"/>
        </el-form-item>
        <el-form-item label="安全库存" prop="safetyStock">
          <el-input v-model="form.safetyStock" placeholder="请输入安全库存"/>
        </el-form-item>
        <el-form-item label="货架" prop="shelf">
          <el-input v-model="form.shelf" placeholder="请输入货架"/>
        </el-form-item>
        <el-form-item label="货架计数" prop="shelfCount">
          <el-input v-model="form.shelfCount" placeholder="请输入货架计数"/>
        </el-form-item>
        <el-form-item label="是否导入" prop="importNot">
          <el-input v-model="form.importNot" placeholder="请输入是否导入"/>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入单价"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 数据导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
          ref="upload"
          :limit="1"
          accept=".xlsx, .xls"
          :headers="upload.headers"
          :action="upload.url"
          :disabled="upload.isUploading"
          :on-progress="handleFileUploadProgress"
          :on-success="handleFileSuccess"
          :auto-upload="false"
          drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
<!--          <div class="el-upload__tip" slot="tip">-->
<!--            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据-->
<!--          </div>-->
          <span>仅允许导入xls、xlsx格式文件。</span>
<!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
	import { listHouse, getHouse, delHouse, addHouse, updateHouse } from '@/api/house/house'
	import { getToken } from "@/utils/auth";
	export default {
		name: 'House',
		dicts: ['unit'],
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
				// 库房备件表格数据
				houseList: [],
				// 弹出层标题
				title: '',
				// 是否显示弹出层
				open: false,
				// 用户导入参数
				upload: {
					// 是否显示弹出层（用户导入）
					open: false,
					// 弹出层标题（用户导入）
					title: "",
					// 是否禁用上传
					isUploading: false,
					// 是否更新已经存在的用户数据
					updateSupport: 0,
					// 设置上传的请求头部
					headers: { Authorization: "Bearer " + getToken() },
					// 上传的地址
					url: process.env.VUE_APP_BASE_API + "/mapper/house/import"
				},
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 12,
					warnings: null,
					storageLocation: null,
					materialName: null,
					materialNumber: null,
					specificationModel: null,
					brand: null,
					unit: null,
					countQuantity: null,
					status: null,
					storageLocationDescription: null,
					safetyStock: null,
					shelf: null,
					shelfCount: null,
					importNot: null,
					price: null,
				},
				// 表单参数
				form: {},
				// 表单校验
				rules: {
					warnings: [
						{ required: true, message: '预警数量不能为空', trigger: 'blur' }
					],
					storageLocation: [
						{ required: true, message: '库位不能为空', trigger: 'blur' }
					],
					materialName: [
						{ required: true, message: '物料名称不能为空', trigger: 'blur' }
					],
				}
			}
		},
		created () {
			this.getList()
		},
		methods: {
			/** 查询库房备件列表 */
			getList () {
				this.loading = true
				listHouse(this.queryParams).then(response => {
					this.houseList = response.rows
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
					id: null,
					warnings: null,
					storageLocation: null,
					materialName: null,
					materialNumber: null,
					specificationModel: null,
					brand: null,
					unit: null,
					countQuantity: null,
					status: null,
					storageLocationDescription: null,
					safetyStock: null,
					shelf: null,
					shelfCount: null,
					importNot: null,
					price: null,
					createBy: null,
					createTime: null,
					updateBy: null,
					updateTime: null
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
				this.ids = selection.map(item => item.id)
				this.single = selection.length !== 1
				this.multiple = !selection.length
			},
			/** 新增按钮操作 */
			handleAdd () {
				this.reset()
				this.open = true
				this.title = '添加库房备件'
			},
			/** 修改按钮操作 */
			handleUpdate (row) {
				this.reset()
				const id = row.id || this.ids
				getHouse(id).then(response => {
					this.form = response.data
					this.open = true
					this.title = '修改库房备件'
				})
			},
			/** 提交按钮 */
			submitForm () {
				this.$refs['form'].validate(valid => {
					if (valid) {
						if (this.form.id != null) {
							updateHouse(this.form).then(response => {
								this.$modal.msgSuccess('修改成功')
								this.open = false
								this.getList()
							})
						} else {
							addHouse(this.form).then(response => {
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
				const ids = row.id || this.ids
				this.$modal.confirm('是否确认删除库房备件编号为"' + ids + '"的数据项？').then(function () {
					return delHouse(ids)
				}).then(() => {
					this.getList()
					this.$modal.msgSuccess('删除成功')
				}).catch(() => {})
			},
			/** 导出按钮操作 */
			handleExport () {
				this.download('mapper/house/export', {
					...this.queryParams
				}, `house_${new Date().getTime()}.xlsx`)
			},
			/** 导入按钮操作 */
			handleImport() {
				this.upload.title = "用户导入";
				this.upload.open = true;
			},
			/** 下载模板操作 */
			importTemplate() {
				this.download('system/user/importTemplate', {
				}, `user_template_${new Date().getTime()}.xlsx`)
			},
			// 文件上传中处理
			handleFileUploadProgress(event, file, fileList) {
				this.upload.isUploading = true;
			},
			// 文件上传成功处理
			handleFileSuccess(response, file, fileList) {
				this.upload.open = false;
				this.upload.isUploading = false;
				this.$refs.upload.clearFiles();
				this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
				this.getList();
			},
			// 提交上传文件
			submitFileForm() {
				this.$refs.upload.submit();
			}
		}
	}
</script>
