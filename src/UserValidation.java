import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
record UserForm(String email, String password, int age) {
    public UserForm {
        if (email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Email and password cannot be blank!");
        }
    }
}
class UserValidator {
    private final List<Predicate<UserForm>> rules = new ArrayList<>();
    public void addRule(Predicate<UserForm> rule) {
        rules.add(rule);
    }
    public boolean isValid(UserForm form) {
        return rules.stream().allMatch(rule -> rule.test(form));
    }
}
public class UserValidation {
    public static void main(String[] args) {
        UserValidator validator = new UserValidator();
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);
        UserForm user = new UserForm("anna@example.com", "secure123", 20);
        System.out.println("Is form valid? " + validator.isValid(user));
    }
}