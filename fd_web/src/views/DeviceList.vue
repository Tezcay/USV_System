<template>
	<div class="page-container">
		<div class="action-bar">
			<el-form :inline="true" size="small">
				<el-form-item label="设备编号">
					<el-input placeholder="请输入编号"></el-input>
				</el-form-item>
				<el-form-item label="设备状态">
					<el-select placeholder="请选择">
						<el-option label="正常" value="normal"></el-option>
						<el-option label="维修中" value="repair"></el-option>
						<el-option label="离线" value="offline"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" icon="el-icon-search">查询</el-button>
					<el-button type="success" icon="el-icon-plus">新增设备</el-button>
				</el-form-item>
			</el-form>
		</div>

		<el-table :data="deviceData" border stripe style="width: 100%">
			<el-table-column prop="deviceId" label="设备编号" width="120"></el-table-column>
			<el-table-column prop="name" label="设备名称"></el-table-column>
			<el-table-column prop="model" label="型号"></el-table-column>
			<el-table-column prop="battery" label="续航(min)"></el-table-column>
			<el-table-column prop="owner" label="归属人"></el-table-column>
			<el-table-column label="状态">
				<template slot-scope="scope">
					<el-tag :type="statusMap[scope.row.status].type">
						{{ statusMap[scope.row.status].text }}
					</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template>
					<el-button type="text" size="small" icon="el-icon-edit">编辑</el-button>
					<el-button type="text" size="small" icon="el-icon-view">详情</el-button>
					<el-button type="text" size="small" icon="el-icon-delete" style="color: #F56C6C">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				statusMap: {
					'正常': {
						type: 'success',
						text: '正常'
					},
					'任务中': {
						type: 'primary',
						text: '任务中'
					},
					'维修中': {
						type: 'warning',
						text: '维修中'
					},
					'离线': {
						type: 'info',
						text: '离线'
					}
				},
				deviceData: [{
						deviceId: 'USV-001',
						name: '探索者1号',
						model: 'T-800',
						battery: '120',
						owner: '张三',
						status: '正常'
					},
					{
						deviceId: 'USV-002',
						name: '猎鹰号',
						model: 'F-22',
						battery: '90',
						owner: '李四',
						status: '任务中'
					}
				]
			}
		}
	}
</script>