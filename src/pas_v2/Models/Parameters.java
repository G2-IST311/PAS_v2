
package pas_v2.Models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author d.mikhaylov
 */
public interface Parameters {
    
    final Set<RoleEnum> OPERATOR = Stream.of(RoleEnum.CHECKIN,RoleEnum.CHECKOUT,RoleEnum.LOOKUP,RoleEnum.VIEW_REPORTS)
            .collect(Collectors.toCollection(HashSet::new));
    
    final Set<RoleEnum> ADMIN = Stream.of(RoleEnum.LOOKUP, RoleEnum.CREATE_EMPLOYEE,RoleEnum.CREATE_PROFILE, RoleEnum.EDIT_PROFILE, RoleEnum.VIEW_REPORTS)
            .collect(Collectors.toCollection(HashSet::new));
    
    
}
