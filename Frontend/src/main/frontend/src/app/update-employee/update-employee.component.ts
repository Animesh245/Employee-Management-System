import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id !: number;
  employee: Employee = new Employee();
  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params[`id`];
    this.employeeService.getEmployeeById(this.id)
    .subscribe(data => {this.employee = data},
      error => console.log(error))
    };

    //onSubmit event will trigger this
    onSubmit(){
      this.employeeService.updateEmployee(this.id, this.employee).subscribe( data => {
        this.goToEmployeeList();
      },error => console.log(error));
    }

    //After updating it will trigger route to this page
    goToEmployeeList(){
      this.router.navigate(['/employees']);
    }
  }

