package cn.xxxx.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义过滤器工厂
 */
@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {
    public static final String KEY = "value";

    public CheckAuthGatewayFilterFactory() {
        super(CheckAuthGatewayFilterFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public GatewayFilter apply(CheckAuthGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String name = exchange.getRequest().getQueryParams().getFirst("name");
                if (!StringUtils.isEmpty(name)) {
                    if (config.getValue().equals(name)) {
                        return chain.filter(exchange);
                    } else {
                        // 返回404
                        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                        return exchange.getResponse().setComplete();
                    }
                }
                // 正常请求
                return chain.filter(exchange);
            }
        };
    }

    public static class Config {
        private String value;

        public Config() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
