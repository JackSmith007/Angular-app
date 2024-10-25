package com.example.Simple.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class SecurityController {
    
	@Autowired
	
	private AuthenticationManager authenticationmanager;
	
	
	@Autowired
	
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	
	private JwtUtil jwtUtil;
	
	@RequestMapping("/hello")
	public String Hello(){
		
		return " Hello app ";
	}
	
	
	@RequestMapping(value = "/auth",method =RequestMethod.POST)
	public ResponseEntity<?>createToken(@RequestBody AuthenticationRequest autheticationrequest)throws Exception{
		  try {
	            // Attempt to authenticate the user using username and password
	            authenticationmanager.authenticate(
	                new UsernamePasswordAuthenticationToken(autheticationrequest.getUsername(), autheticationrequest.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("Incorrect username or password", e);
	        }
		  
		  final UserDetails userDetails = myUserDetailsService.loadUserByUsername(autheticationrequest.getUsername());

	        // Generate JWT token
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        // Return the token as a response
	        return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	        
    
	
}}
