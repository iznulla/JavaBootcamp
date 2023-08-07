package school21.spring.service.models;


import lombok.Data;
import lombok.Getter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import school21.spring.service.annotations.OrmColumn;
import school21.spring.service.annotations.OrmColumnId;
import school21.spring.service.annotations.OrmEntity;

@Data
@OrmEntity(table = "Users")
public class User {
  public User() {}
  @Getter @Setter @OrmColumnId
  private Long identifier;
  @Getter @Setter @OrmColumn(name = "email", length = 128) @NonNull
  private String email;
}
