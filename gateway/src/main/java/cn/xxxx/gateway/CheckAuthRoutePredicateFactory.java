package cn.xxxx.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言工厂
 */
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {
    public static final String PARAM_KEY = "param";

    public CheckAuthRoutePredicateFactory() {
        super(CheckAuthRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY);
    }

    public Predicate<ServerWebExchange> apply(CheckAuthRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            public boolean test(ServerWebExchange exchange) {
                if (config.getParam().equals("zhangsan")) {
                    return true;
                }
                return false;
            }


        };
    }

    @Validated
    public static class Config {
        @NotEmpty
        private String param;

        public Config() {
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}
