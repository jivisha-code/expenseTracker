package com.project.lms.ExpenseTrackerLms.controller;

//import com.project.lms.ExpenseTrackerLms.dto.DeleteIncomeDTO;
import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
import com.project.lms.ExpenseTrackerLms.dto.IncomeDTO;
import com.project.lms.ExpenseTrackerLms.entity.Income;
import com.project.lms.ExpenseTrackerLms.services.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class IncomeController {

    private final IncomeService incomeService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getIncomeByUserId(@PathVariable Long userId) {
//        System.out.println("hereeee"+userId);
        try {
            return ResponseEntity.ok(incomeService.getIncomeByUserId(userId)); // This should work now
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }



    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDTO incomeDTO){
        Income createdIncome = incomeService.postIncome(incomeDTO);
        if(createdIncome !=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @PutMapping("/updateIncome")
    public ResponseEntity<?> updateIncome(@RequestBody IncomeDTO incomeDTO){
        try {
            return ResponseEntity.ok(incomeService.updateIncome(incomeDTO));
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id){
        try{
          incomeService.deleteIncome(id);
          return ResponseEntity.ok(null);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteIncomeByUserId(DeleteIncomeDTO deleteIncomeDTO) {
//        try {
//            incomeService.deleteAllByUserId(deleteIncomeDTO.getUserId());
//            return ResponseEntity.ok().build();
//        } catch (EntityNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//        }
//    }

//    @GetMapping("getIncomeByUserId/{userId}")
//    public ResponseEntity<?> getIncomeByUserId(@PathVariable Long userId){
//        return ResponseEntity.ok(incomeService.getIncomeByUserId(userId));
//    }
}

