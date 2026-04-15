<template>
	<div class="dashboard-wrapper">
		<el-row :gutter="20">
			<el-col :span="6" v-for="item in stats" :key="item.title">
				<el-card shadow="never" class="stats-card">
					<div class="stats-icon" :style="{ background: item.bgColor }">
						<i :class="item.icon"></i>
					</div>
					<div class="stats-info">
						<p class="stats-title">{{ item.title }}</p>
						<p class="stats-number">{{ item.count }}</p>
					</div>
				</el-card>
			</el-col>
		</el-row>

		<el-row :gutter="20" class="mt-20">
			<el-col :span="16">
				<el-card shadow="never" class="chart-box">
					<div slot="header" class="clearfix">
						<span class="card-title"><i class="el-icon-s-data"></i> 近期巡防任务趋势</span>
					</div>
					<div class="mock-chart">
						<p>ECharts 趋势图表加载中...</p>
					</div>
				</el-card>
			</el-col>

			<el-col :span="8">
				<el-card shadow="never" class="quick-nav">
					<div slot="header">
						<span class="card-title"><i class="el-icon-menu"></i> 快捷功能</span>
					</div>
					<div class="nav-grid">
						<div class="nav-item" @click="$router.push('/device')">
							<i class="el-icon-ship"></i><span>设备录入</span>
						</div>
						<div class="nav-item" @click="$router.push('/route')">
							<i class="el-icon-map-location"></i><span>航线规划</span>
						</div>
						<div class="nav-item" @click="$router.push('/task')">
							<i class="el-icon-video-play"></i><span>创建任务</span>
						</div>
						<div class="nav-item" @click="$router.push('/result')">
							<i class="el-icon-collection"></i><span>结果查询</span>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				// 这里的数值后续通过 Axios 从后端接口获取 
				stats: [{
						title: '设备总数',
						count: '12',
						icon: 'el-icon-ship',
						bgColor: '#409EFF'
					},
					{
						title: '航线总数',
						count: '8',
						icon: 'el-icon-map-location',
						bgColor: '#67C23A'
					},
					{
						title: '任务总数',
						count: '156',
						icon: 'el-icon-s-promotion',
						bgColor: '#E6A23C'
					},
					{
						title: '归档结果',
						count: '142',
						icon: 'el-icon-folder-checked',
						bgColor: '#909399'
					}
				]
			}
		}
	}
</script>

<style scoped>
	.mt-20 {
		margin-top: 20px;
	}

	.stats-card {
		display: flex;
		align-items: center;
		border: 1px solid #ebeef5;
	}

	.stats-icon {
		width: 60px;
		height: 60px;
		border-radius: 8px;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #fff;
		font-size: 30px;
		margin-right: 15px;
	}

	.stats-title {
		font-size: 14px;
		color: #909399;
		margin: 0;
	}

	.stats-number {
		font-size: 24px;
		font-weight: bold;
		margin: 5px 0 0 0;
		color: #303133;
	}

	.card-title {
		font-weight: bold;
		color: #303133;
	}

	.mock-chart {
		height: 300px;
		background: #f8f9fa;
		border-radius: 4px;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #999;
	}

	.nav-grid {
		display: grid;
		grid-template-columns: 1fr 1fr;
		gap: 15px;
	}

	.nav-item {
		background: #f5f7fa;
		padding: 20px 10px;
		border-radius: 8px;
		text-align: center;
		cursor: pointer;
		transition: 0.3s;
	}

	.nav-item:hover {
		background: #ecf5ff;
		color: #409EFF;
	}

	.nav-item i {
		font-size: 24px;
		display: block;
		margin-bottom: 8px;
	}

	.nav-item span {
		font-size: 13px;
	}
</style>