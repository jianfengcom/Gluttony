package or.dozer;

import lombok.Data;
import org.dozer.Mapping;

/**
 * @Description:
 * @Author
 * @Date 2021/1/14
 * @Version 1.0
 */
@Data
public class CopyNinja {
    private Long ninjaId;
    private String nation;

    /*
    You put @Mapping annotation either on getter of field directly. If Dozer finds that it adds a bi-directional mapping.
    It means that putting annotation once will create mappings for both conversion types.
     */
    @Mapping("nation")
    private String realNation;
}
