package com.maker;
/**
 *        Actuator可视化监控
 *          在Spring全家桶中，针对SpringBoot的监控提供了一个SpringBootAdmin组件，这个组件是由官方所提供的
 *          但是要想充分理解此组件之中所有可能面对的使用问题，最佳的做法是结合SpringCloud技术
 *          所以此次所使用的SpringBoot监控是另一套开源的版本
 *
 *          SpringBoot主机一般都要单独部署在一台微服务主机上，但是这台主机对外暴露监控数据（Actuator实现）
 *          ，除了提供这些基础的监控信息外，还需要提供本主机的监控数据（内存占用率，CPU使用率等等），那么常规的
 *          做法就是结合NodeExporter组件来获取这些内容
 *
 *          环境搭建
 *              在VMware中创建三台linux虚拟机，microboot-produce、microboot-promtheus、microboot-garana
 *              在produce上搭建Node_Exporter组件
 *              在项目中引入Prometheus依赖implementation 'io.micrometer:micrometer-registry-prometheus:1.7.3'
 *              引用了prometheus后，可以按照prometheus自己的格式来进行数据的获取
 *              此时只需要开放相应的接口即可，在application.yml当中进行相应的配置
 *              配置完成后，将项目打包处理，上传到linux（produce）虚拟机上
 *
 *              搭建prometheus主机环境：
 *              下载prometheus组件到prometheus虚拟机上
 *                  https://github.com/prometheus/prometheus/releases/download/v2.37.0/prometheus-2.37.0.linux-amd64.tar.gz
 *               解压安装好prometheus后，修改prometheus中的prometheus.yml配置文件
 *               global: #全局配置
 *                   scrape_interval: 15s #数据的抓取间隔
 *                   evaluation_interval: 15s #数据评估的间隔
 *
 *                scrape_configs:   #抓取配置
 *                  - job_name: "prometheus"
 *                    static_configs:
 *                      - targets: ["microboot-promethues:9999"] #监控的主机
 *                  - job_name: "node"
 *                    static_configs:
 *                      - targets: ["microboot-produce:9100"]
 *                      lables: #标签，用来标识不同的node
 *                          instance:microboot-produce-node
 *                  - job_name: "microboot"
 *                  scrape_interval: 10s #局部的抓取时间配置
 *                  scrape_timeout:5s #抓取数据超时时间
 *                  metrics_path: '/actuator/prometheus' #配置获取数据的地址
 *                    static_configs:
 *                      - targets: ["microboot-produce:9090"]
 *
 *          配置完成yml文件后，使用如下命令检查yml文件的语法：
 *              /usr/local/prometheus/promtool check config /usr/local/prometheus/prometheus.yml
 *
 *          为了便于后面的节点管理，建议做一个启动配置文件，实现prometheus服务的自启动
 *              进入/usr/lib/systemd/system/目录，创建一个promethues.service文件
 *              [Unit]
 *              Description=Node_Exporter Service
 *
 *              [Service]
 *              User=root
 *              ExecStart=/usr/local/prometheus/prometheus \ #指定执行的程序
 *                  --config.file=/usr/local/prometheus/prometheus.yml #指定prometheus的配置文件
 *                  --storage.tsdb.path=/usr/local/prometheus/data \ #指定prometheus数据库存放数据的目录
 *                  --web.listen-address=0.0.0.0:9999 --web.enable-lifecycle #指定prometheus监听的端口
                TimeoutStopSec=10
                Restart=on-failure
                RestartSec=5

                [Install]
                WantedBy=multi-user.target


 *          重新加载服务配置文件
 *          systemctl daemon-reload
 *          服务自启动设置
 *           systemctl enable prometheus.service
 *          服务启动
 *           systemctl start prometheus.service
 *          在防火墙之中开放端口
 *           firewall-cmd --zone=public --add-port=9999/tcp --permanent
 *           firewall-cmd --reload
 *
 *
 *        Grafana环境搭建
 *          下载Grafana组件
 *          在linux下安装Grafana
 *          配置grafana.service文件，进行服务自启动
 *
 *          [Unit]
 *          Description=Grafana Service
 *
 *          [Service]
 *          User=root
 *          ExecStart=/usr/local/grafana/bin/grafana-server \
 *              -config /usr/local/grafana/conf/defaults.ini -homepath /usr/local/grafana
 *          TimeoutStopSec=10
 *          Restart=on-failure
 *          RestartSec=5
 *
 *          [Install]
 *          WantedBy=multi-user.target
 *
 *          重新加载service文件,设置服务自启动并开启服务
 *          systemctl daemon-reload
 *          systemctl enable grafana.service
 *          systemctl start grafana.service
 *
 *          开启grafana 3000端口
 *           firewall-cmd --zone=public --add-port=3000/tcp --permanent
 *           firewall-cmd --reload
 *
 *           访问grafana：http://microboot-gragana:3000/
 *           默认账户：admin / admin
 *
 *           在Grafana中配置Prometheus数据源
 *
 *
 *      监控警报：
 *          Actuator提供微服务的监控数据，Prometheus提供了微服务监控数据的存储，利用Grafana
 *          提供了微服务监控数据的可视化
 *          但是Prometheus中除了能提供监控数据的存储外，还可以直接提供警报服务
 *
 *          如果想要进行警报操作，首先得获取警报组件
 *          下载alertmanager，下载解压完成后，对alertmanager.yml进行配置
 *          global:
 *              resolve_timeout: 5m
 *              smtp_smarthost: 'smtp.qq.com:25'
 *              smtp_from: '953668865@qq.com'
 *              smtp_auth_username: '953668865@qq.com'
 *              smtp_auth_password: 'vczywxnzljihbead'
 *              smtp_require_tls: false
 *
 *          route:
 *              group_by: ['alertname']
 *              group_wait: 10s
 *              group_interval: 10s
 *              repeat_interval: 1h
 *              receiver: 'mail'
 *          receivers:
 *          - name: 'mail'
 *            email_configs:
 *              - to: '953668865@qq.com
 *
 *        配置完成后，利用amtool检查yml文件的语法
 *          /usr/local/alertmanager/amtool check-config /usr/local/alertmanager/alertmanager.yml
 *        配置alertmanager自启动服务
 *          [Unit]
 *          Description=Grafana Service
 *
 *          [Service]
 *          User=root
 *          ExecStart=/usr/local/alertmanager/alertmanager \
 *              --config.file=/usr/local/alertmanager/alertmanager.yml
 *          TimeoutStopSec=10
 *          Restart=on-failure
 *          RestartSec=5
 *
 *          [Install]
 *          WantedBy=multi-user.target
 *        重新加载service文件
 *          systemctl daemon-reload
 *        设置服务自启动并开启服务
 *         systemctl enable alertmanager.service
 *         systemctl start alertmanager.service
 *
 *        查看端口占用情况
 *          netstat -nptl
 *        alertmanager占用的是9093和9094两个端口，在防火墙中开放两个端口
 *          firewall-cmd --zone=public --add-port=9093/tcp --permanent
 *          firewall-cmd --zone=public --add-port=9094/tcp --permanent
 *
 *        修改prometheus.yml将alertmanager和prometheus关联起来
 *        alerting:
 *          alertmanagers:
 *              - static_configs:
 *              - targets:
 *                  - microboot-prometheus:9093
 *
 *         设置规则
 *          创建rules目录，在里面创建两个yml文件microboot-acutator-rule.yml和microboot.node.rules
 *          分别对微服务项目和服务器主机资源进行报警
 *          【microboot-acutator-rule.yml】
 *          groups:
 *          - name: microboot.actuator.rules
 *            rules:
 *              - alert: MicrobootInstanceDown
 *                expr: up{job="microboot"} == 0
 *                for: 1m
 *                labels:
 *                  severity: warning
 *                annotations:
 *                  description: "微服务 {{ $labels.instance }} 关闭"
 *                  summary: "运行在 {{ $labels.instance }} 主机中的 {{ $labels.job }} 微服务已经关闭了！"
 *
 *            【microboot.node.rules】
 *              groups:
             * - name: microboot.node.rules
             *   rules:
             *   - alert: NodeCPUUsage
             *     expr: 100 - (avg(irate(node_cpu_seconds_total{mode="idle"}[5m])) by (instance) * 100) > 80
             *     for: 2m
             *     labels:
             *       severity: warning
             *     annotations:
             *       summary: "微服务运行主机 {{ $labels.instance }} 中的CPU使用率过高"
             *       description: "微服务运行主机 {{ $labels.instance }} 中的CPU使用大于80%，当前值: “{{ $value }}”"
             *   - alert: NodeMemoryUsage
             *     expr: 100 - (node_memory_MemFree_bytes+node_memory_Cached_bytes+node_memory_Buffers_bytes) / node_memory_MemTotal_bytes * 100 > 80
             *     for: 2m
             *     labels:
             *       severity: warning
             *     annotations:
             *       summary: "微服务运行主机 {{ $labels.instance }} 中的内存使用率过高"
             *       description: "微服务运行主机 {{ $labels.instance }} 内存使用大于 80%，当前值: {{ $value }}"
             *   - alert: NodeFilesystemUsage
             *     expr: 100 - (node_filesystem_free_bytes{fstype=~"ext4|xfs"} / node_filesystem_size_bytes{fstype=~"ext4|xfs"} * 100) > 90
             *     for: 2m
             *     labels:
             *       severity: warning
             *     annotations:
             *       summary: "微服务运行主机 {{ $labels.instance }}中的“{{ $labels.mountpoint }}” 分区使用过高"
             *       description: "微服务运行主机 {{ $labels.instance }} 中 {{ $labels.mountpoint }} 分区使用大于80%，当前值: {{ $value }}"
             *
             *
 *          修改prometheus.yml的rule_files项
 *              rule_files:
 *                  - "rules/*.yml"
 *          重启prometheus服务
 *
 *          访问prometheus地址，查看配置是否成功：
 *              http://microboot-prometheus:9999/targets
 *
 *          由于虚拟机采用的是仅主机的方式，所以无法成功发出警告邮件到公网邮箱
 * */
public class ActuatorTest {
}
