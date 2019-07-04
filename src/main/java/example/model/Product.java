package example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @ManyToOne(optional = false) Color newStyleColor;

  @Enumerated(EnumType.STRING)
  Color oldStyleColor;
}
