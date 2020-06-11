module.exports = {
    configureWebpack: {
        resolve: {
            // 配置可省略哪些后缀
            extensions: [],
            // 起别名
            alias: {
                // 'src': '@', //在脚手架里已经被配置过了
                'assets': '@/assets',
                'common': '@/common',
                'components': '@/components',
                'network': '@/network',
                'router': '@/router',
                'store': '@/store',
                'views': '@/views',
            }
        }
    }
}