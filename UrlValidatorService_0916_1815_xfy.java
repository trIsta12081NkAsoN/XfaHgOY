// 代码生成时间: 2025-09-16 18:15:06
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.net.URL;
import javax.annotation.PostConstruct;

@Service
public class UrlValidatorService {

    private RestTemplate restTemplate;

    // 注入RestTemplate，用于发送HTTP请求
    public UrlValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 验证URL链接的有效性
    public boolean isValidUrl(String url) {
        try {
            // 尝试将字符串转换为URL对象
            new URL(url);
            // 发送HEAD请求检查URL是否可达
            ResponseEntity<String> response = restTemplate.headForHeaders(url);
            // 返回链接是否有效
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            // 处理异常，记录日志
            // 这里省略日志记录代码，实际项目中应添加日志记录
            return false;
        }
    }

    // 组件初始化后进行资源检查
    @PostConstruct
    public void init() {
        // 可以在这里添加一些初始化代码，例如检查网络连接等
    }
}
