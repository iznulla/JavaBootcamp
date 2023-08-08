package school21.spring.service.models;

import javax.annotation.processing.Processor;
import lombok.Data;
import lombok.Getter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import school21.spring.service.annotations.OrmColumn;
import school21.spring.service.annotations.OrmColumnId;
import school21.spring.service.annotations.OrmEntity;

@Data
@OrmEntity(table = "User")
public class User {
  @OrmColumnId
  private Long identifier;
  @OrmColumn(name = "email", length = 128)
  private String email;
}
