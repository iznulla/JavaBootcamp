package school21.edu.models;

import school21.edu.annotations.OrmColumn;
import school21.edu.annotations.OrmColumnId;
import school21.edu.annotations.OrmEntity;

@OrmEntity(table = "Comps")
public class Computer {
  public Computer() {}
  public Computer(String brandName, Integer counts) {
    this.brandName = brandName;
    this.counts = counts;
  }
  @OrmColumnId
  private Integer id;
  @OrmColumn(name = "Brand", length = 20)
  private String brandName;

  @OrmColumn(name = "counts")
  private Integer counts;

  public Integer getId() {
    return id;
  }

  public String getBrandName() {
    return brandName;
  }

  public Integer getCounts() {
    return counts;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void setCounts(Integer counts) {
    this.counts = counts;
  }
}
