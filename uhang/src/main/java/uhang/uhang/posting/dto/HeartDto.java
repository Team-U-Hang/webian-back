package uhang.uhang.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uhang.uhang.login.domain.Member;
import uhang.uhang.posting.domain.entity.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartDto {
    private long post;
}
