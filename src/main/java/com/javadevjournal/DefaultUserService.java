package com.javadevjournal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
   

    @Override
    public void register(UserData user) throws UserAlreadyExistException {

        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        UserEntity userEntity = new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        userRepository.save(userEntity);

    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    private void encodePassword( UserEntity userEntity, UserData user){
        userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
    }
	


	@Override
	public void sendRegistrationConfirmationEmail(UserEntity user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyUser(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserEntity getUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
