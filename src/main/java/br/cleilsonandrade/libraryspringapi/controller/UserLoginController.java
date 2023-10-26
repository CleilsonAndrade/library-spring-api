package br.cleilsonandrade.libraryspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cleilsonandrade.libraryspringapi.dto.UserLoginDTO;
import br.cleilsonandrade.libraryspringapi.dto.UserRegisterDTO;
import br.cleilsonandrade.libraryspringapi.dto.UserTokenDTO;
import br.cleilsonandrade.libraryspringapi.entity.User;
import br.cleilsonandrade.libraryspringapi.service.JWTService;
import br.cleilsonandrade.libraryspringapi.service.UserService;

@RestController
@RequestMapping(value = "/v1/api")
public class UserLoginController {

  @Autowired
  private UserService userService;

  @Autowired
  private JWTService jwtService;

  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
    var newUser = this.userService.registerUser(userRegisterDTO);
    return ResponseEntity.ok(newUser);
  }

  @PostMapping("/login")
  public ResponseEntity<UserTokenDTO> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
    var tokenUser = this.jwtService.authenticateUser(userLoginDTO);

    return ResponseEntity.ok(tokenUser);
  }
}
