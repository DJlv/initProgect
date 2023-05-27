<template>
	<view >
		<view style="background-color: antiquewhite;text-align: left;font-size: 20px;" >备件名称:{{ queryParams.materialName }}</view>
		<view style="background-color: antiquewhite;text-align: left;font-size: 20px;" >备件位置:{{ queryParams.storageLocation }}</view>
		
		<view style="background-color: antiquewhite;text-align: left;font-size: 20px;" >规格型号:{{ queryParams.specificationModel }}</view>
		
		<view style="background-color: antiquewhite;text-align: left;font-size: 20px;" >品牌:{{ queryParams.brand }}</view>
		
		<view style="background-color: antiquewhite;text-align: left;font-size: 20px;" >盘点数量:{{ queryParams.countQuantity }}</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				queryParams: {
					pageNum: "1",
					pageSize: "15",
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
		methods: {
			onLoad(option) {

				this.queryParams = JSON.parse(decodeURIComponent(option.queryParams));
				console.log(this.queryParams)
			}
		}
	}
</script>

<style>

</style>