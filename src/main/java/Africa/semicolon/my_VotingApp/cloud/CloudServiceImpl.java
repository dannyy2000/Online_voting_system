package Africa.semicolon.my_VotingApp.cloud;

import Africa.semicolon.my_VotingApp.exception.ImageUploadException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class CloudServiceImpl implements CloudService {

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile image) {
        try{
            Map<?,?> response =
                    cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                    log.info("response::{}",response);
                    return response.get("url").toString();
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage() );
        }
    }
}
