import { Component, OnInit, Output } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { EmployeeService } from '../employee.service';
import { Subject } from 'rxjs';
import { EmployeeAddEditComponent } from './employee-add-edit.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-employeelist',
  templateUrl: './employeelist.component.html',
  styleUrl: './employeelist.component.css'
})
export class EmployeelistComponent implements OnInit {

  //action (for methods: get/update/delete)
  displayColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'email',
    'dob',
    'gender',
    'salary',
    'action'
  ]

  firstName!: string
  lastName!: string
  email!: string
  dob!: Date
  gender!: string
  salary!: string

  dataSource!: MatTableDataSource<any>

  constructor(private employeeService: EmployeeService, public dialog: MatDialog) {
  }

  //all methods are optionals, returns an observable object
  ngOnInit(): void {
    this.fetchEmployeeData()
  }

  fetchEmployeeData() {
    this.employeeService.getEmployees().subscribe({
      next: (data) => {
        this.dataSource = new MatTableDataSource<any>(data)
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  openAddNewEmployeeDialog() {

  }


  updateEmployee(data: any) {
    const dialogRef = this.dialog.open(EmployeeAddEditComponent, {
      data: {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        dob: this.dob,
        gender: this.gender,
        salary: this.salary
      }
    })

    dialogRef.afterClosed().subscribe(
      result => {
        console.log('Dialog is closed')
        this.email = result
      }
    )
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe({
      next: (result) => {
        alert('Employee deleted')
        this.fetchEmployeeData()
      },

      error: (err) => {
        console.error(err)
        
      }
    })
  }

}
