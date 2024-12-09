package TranQuocToan.Java.DoAn.Controller;

import TranQuocToan.Java.DoAn.Model.Users;
import TranQuocToan.Java.DoAn.Service.UserManagementService;
import TranQuocToan.Java.DoAn.dto.ReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




@CrossOrigin("http://localhost:5173")
@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quizzes")
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(userManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.login(req));
    }


    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer userId ) {
        return ResponseEntity.ok(userManagementService.getUsersById(userId));
    }




    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> getUpdate(@PathVariable Integer userId, @RequestBody Users reqres) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = authentication.getName();
       ReqRes response = userManagementService.getMyInfo(email);
       return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @DeleteMapping ("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> getDelete(@PathVariable Integer userId, @RequestBody Users reqres) {
        return ResponseEntity.ok(userManagementService.deleteUser(userId));
    }

}