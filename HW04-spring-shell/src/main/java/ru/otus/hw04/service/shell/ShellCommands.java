package ru.otus.hw04.service.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.service.ExamService;
import ru.otus.hw04.service.login.LoginService;
import ru.otus.hw04.service.login.User;

import java.util.Objects;

@ShellComponent
public class ShellCommands {

    private User user;
    private final ExamService examService;
    private final LoginService loginService;

    public ShellCommands(ExamService examService, LoginService loginService) {
        this.examService = examService;
        this.loginService = loginService;
    }

    @ShellMethod(value = "Login command.", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "admin") String username) throws ModuleException {
        this.user = loginService.login(username);
        return "Добро пожаловать: " + username;
    }

    @ShellMethod(value = "Starts exam.", key = {"se", "start-exam"})
    @ShellMethodAvailability(value = "loginSucceedAvailability")
    public void startExam() throws ModuleException {
        examService.startExam(user);
    }

    @ShellMethod(value = "Prints last result of current user.", key = {"lr", "last-result"})
    @ShellMethodAvailability(value = "loginSucceedAvailability")
    public void printLastResult() throws ModuleException {
        examService.printLastResult(user.getName());
    }

    @ShellMethod(value = "Print user last result. Available only for username \"admin\".", key = {"ulr", "user-last-result"})
    @ShellMethodAvailability(value = "loginAdminSucceedAvailability")
    public void printUserLastResult(@ShellOption String username) throws ModuleException {
        examService.printLastResult(username);
    }

    private Availability loginSucceedAvailability() {
        return user != null ? Availability.available() : Availability.unavailable("Access denied. Please login");
    }

    private Availability loginAdminSucceedAvailability() {
        return user != null && Objects.equals(user.getName(), "admin") ? Availability.available() : Availability.unavailable("Access denied. Please login as admin");
    }
}
