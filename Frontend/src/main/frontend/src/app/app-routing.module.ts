import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { LoginComponent } from './login/login.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

const routes: Routes = [
  //by default routing
  {
    path:'', 
    redirectTo: 'employees', 
    pathMatch: 'full'
  },
  //for all employees
  {
    path: 'employees',
    component: EmployeeListComponent,
    canActivate: [AuthGuard]
  },
  //routing for create-employee
  {
    path: 'create-employee', 
    component: CreateEmployeeComponent,
    canActivate: [AuthGuard]
  },
  //for update-employee
  {
    path: 'update-employee/:id',
    component: UpdateEmployeeComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  //for view-employee details
  {
    path: 'employee-details/:id',
    component: EmployeeDetailsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
    },
  //for login
  {
    path: 'login',
   component:LoginComponent,
    pathMatch:"full",
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
