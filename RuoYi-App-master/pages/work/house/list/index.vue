<template>
	<view>
		<view class="content" style="solid 1rpx;">
			<uni-easyinput class="uni-mt-5" suffixIcon="search" v-model="queryParams.materialName" placeholder="右侧图标"
				@iconClick="selectAll(1)"></uni-easyinput>
		</view>
		<view style="flex:0 1 calc(100vh - 200rpx)">
		<uni-section title="备件名称" type="line">
			<uni-table ref="table" border stripe type="selection">
				<uni-tr v-for="(item, index) in houseList" :key="index">
					<view class="list-cell list-cell-arrow" @click="handleToEditInfo(item)">
						<view class="menu-item-box">
							<view>{{ item.materialName }}</view>
						</view>
					</view>
				</uni-tr>
			</uni-table>
		</uni-section>		
		</view>


	</view>

</template>

<script>
	import storage from '@/utils/storage'
	import {
		listHouse
	} from "@/api/house/house.js"
	export default {
		data() {
			return {
				houseList: [],
				total: "",
				queryParams: {
					pageNum: "1",
					pageSize: "99999",
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
			}
		},
		created() {
			this.iconClick()
		},
		methods: {
			selectAll(value) {
				this.total = 0
				this.pageNums =1
				console.log(this.pageNums)
				this.queryParams.pageNum = "1"
				console.log(this.queryParams)
				this.iconClick()
			},
			change(e) {
				this.queryParams.pageNum = e.current.toString();
				this.iconClick()
			},
			handleToEditInfo(value) {
				console.log(value)
				this.$tab.navigateTo('/pages/work/house/list/info?queryParams=' + encodeURIComponent(JSON.stringify(
					value)))
			},
			iconClick() {
				listHouse(this.queryParams).then(response => {
					if (response.code == 200) {
						this.houseList = response.rows
						this.total = response.total
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #fff;
		min-height: 100%;
		height: auto;
	}

	.content {
		margin-left: 30px;
		margin-right: 30px;
	}
</style>