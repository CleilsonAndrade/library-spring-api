package br.cleilsonandrade.libraryspringapi.entity;

import java.io.Serializable;

import br.cleilsonandrade.libraryspringapi.dto.UserRegisterDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "tb_user")
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "email")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String email;

  private String password;

  public User(UserRegisterDTO userRegisterDTO) {
    this.name = userRegisterDTO.name();
    this.email = userRegisterDTO.email();
    this.password = userRegisterDTO.password();
  }
}
