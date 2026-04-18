<template>
	<div style="padding: 20px;">
		<h2>🛡️ 系统操作日志监控</h2>
		<el-table :data="logList" border stripe style="width: 100%; margin-top: 20px;">
			<el-table-column prop="id" label="日志ID" width="80" align="center" />
			<el-table-column prop="operator" label="操作人" width="120" />
			<el-table-column prop="content" label="动作描述" />
			<el-table-column prop="ipAddress" label="来源 IP" width="150" />
			<el-table-column prop="operateTime" label="发生时间" width="200" />
		</el-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				logList: []
			}
		},
		mounted() {
			this.getLogs();
		},
		methods: {
			getLogs() {
				const token = sessionStorage.getItem('token') || '';

				this.$axios.get('http://localhost:8080/log/list', {
						headers: {
							'token': token
						}
					})
					.then(res => {
						// 如果后端返回成功
						if (res.data.id === 1 || res.data.id === 200) {

							// 🏆 绝杀！精准扒开 datas 和 sysLogs 这两层外衣，拿到真正的数组！
							if (res.data.datas && res.data.datas.sysLogs) {
								this.logList = res.data.datas.sysLogs;
							} else {
								this.logList = [];
							}

						} else {
							this.$message.error('获取日志失败：' + res.data.message);
						}
					})
					.catch(err => {
						console.error("网络请求彻底失败：", err);
					});
			}
		}
	}
</script>