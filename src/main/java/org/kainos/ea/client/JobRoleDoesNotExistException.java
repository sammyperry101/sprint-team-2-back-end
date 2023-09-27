package org.kainos.ea.client;

<<<<<<< HEAD
public class JobRoleDoesNotExistException extends RuntimeException {

    @Override
    public String getMessage() {
=======
public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
>>>>>>> main
        return "Job Role doesn't exist";
    }
}
