package com.javadevjournal;

public interface UserService {

	 void register(final UserData user) throws UserAlreadyExistException;
	    boolean checkIfUserExist(final String email);
	    void sendRegistrationConfirmationEmail(final UserEntity user);
	    boolean verifyUser(final String token);
	    UserEntity getUserById(final String id);

}
