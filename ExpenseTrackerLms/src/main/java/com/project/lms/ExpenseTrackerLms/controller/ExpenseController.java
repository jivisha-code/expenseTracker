package com.project.lms.ExpenseTrackerLms.controller;


import com.project.lms.ExpenseTrackerLms.dto.ExpenseDTO;
import com.project.lms.ExpenseTrackerLms.entity.Expense;
import com.project.lms.ExpenseTrackerLms.services.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@RequestParam Long userId) {
        List<Expense> expenses = expenseService.getExpenseByUserId(userId);
        return ResponseEntity.ok(expenses);
    }


//    @PostMapping
//    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto){
//        Expense createdExpense= expenseService.postExpense(dto);
//
//        if(createdExpense!= null){
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

    @PostMapping("")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        System.out.println(expenseDTO);
//        if (expenseDTO.getUserId() == null) {
//            return ResponseEntity.badRequest().body("User ID is required");
//        }
        Expense expense = expenseService.postExpense(expenseDTO);
        return ResponseEntity.ok(expense);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("getExpenseByUserId/{userId}")
    public ResponseEntity<?> getExpenseByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(expenseService.getExpenseByUserId(userId));
    }

    @PutMapping("/updateExpense")
    public ResponseEntity<?> updateExpense(@RequestBody ExpenseDTO dto){
        try{
            return ResponseEntity.ok(expenseService.updateExpense(dto));
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
//        try{
//           expenseService.deleteExpense(id);
//           return ResponseEntity.ok(null);
//        }
//        catch (EntityNotFoundException ex){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//        }
//    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpenseByUserId(@PathVariable Long expenseId) {
//        System.out.println("hereee"+userId);
        try {
            expenseService.deleteExpenseByUserId(expenseId);
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("message","All expenses deleted for user with ID: " + expenseId);
            return ResponseEntity.ok(stringMap);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

}
