package com.farm.api.ebytr.staff.controllers;

import com.farm.api.ebytr.staff.controllers.dto.AuthDto;
import com.farm.api.ebytr.staff.controllers.response.LoginResponseDtop;
import com.farm.api.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Comment.
   */

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDtop> login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authDto.username(),
        authDto.password()
    );

    Authentication auth = this.authenticationManager.authenticate(usernamePassword);

    //    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String token = tokenService.generateToken((UserDetails) auth.getPrincipal());
    System.out.println(token);
    return ResponseEntity.ok(new LoginResponseDtop(token));
  }
}