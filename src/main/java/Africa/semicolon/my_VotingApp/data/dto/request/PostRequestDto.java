package Africa.semicolon.my_VotingApp.data.dto.request;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostRequestDto {
    private Set<Long> candidatesId;
    private String description;
    private MultipartFile candidateImage;
    private String patch;

}
