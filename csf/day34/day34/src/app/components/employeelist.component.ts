import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { EmployeeService } from '../employee.service';

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

  dataSource!: MatTableDataSource<any>

  constructor(private employeeService: EmployeeService) {

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

}
