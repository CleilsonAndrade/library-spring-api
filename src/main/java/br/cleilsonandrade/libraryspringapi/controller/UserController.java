package br.cleilsonandrade.libraryspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cleilsonandrade.libraryspringapi.entity.User;
import br.cleilsonandrade.libraryspringapi.service.UserService;

@RestController
@RequestMapping("/auth/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping(value = "/{email}")
  public ResponseEntity<User> getUser(@PathVariable String email, @RequestHeader("Authorization") String header) {
    var findUser = this.userService.findUser(email, header);
    return ResponseEntity.ok(findUser);
  }

  @DeleteMapping(value = "/{email}")
  public ResponseEntity<Void> deleteUser(@PathVariable String email, @RequestHeader("Authorization") String header) {
    this.userService.deleteUser(email, header);
    return ResponseEntity.ok().build();
  }
}
