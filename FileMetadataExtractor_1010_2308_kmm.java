// 代码生成时间: 2025-10-10 23:08:46
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
# 改进用户体验
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
# 改进用户体验

@Component
public class FileMetadataExtractor {

    private static final String FILE_NOT_FOUND_ERROR = "File not found: %s";
# FIXME: 处理边界情况
    private static final String INVALID_FILE_TYPE_ERROR = "Invalid file type: %s";

    public Map<String, Object> extractMetadata(MultipartFile file) {
        Map<String, Object> metadata = new HashMap<>();

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("The file must not be null or empty");
        }

        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();

            // Extract file extension
            String extension = Optional.ofNullable(fileName)
                .map(n -> n.substring(n.lastIndexOf(".") + 1))
                .orElse("");

            // Add metadata to the map
            metadata.put("name", fileName);
            metadata.put("type", contentType);
            metadata.put("size", size);
# 扩展功能模块
            metadata.put("extension", extension);

            // Extract additional metadata if required, like last modified date,
            // checksum, etc., by saving the file temporarily and reading from the filesystem
            // For brevity, this part is omitted

            return metadata;

        } catch (IOException e) {
            throw new RuntimeException(String.format(FILE_NOT_FOUND_ERROR, file.getOriginalFilename()), e);
        } catch (Exception e) {
            throw new RuntimeException(String.format(INVALID_FILE_TYPE_ERROR, file.getContentType()), e);
        }
    }

    // Additional methods to handle specific file types or more complex metadata extraction can be added here
# TODO: 优化性能
}
# 添加错误处理
