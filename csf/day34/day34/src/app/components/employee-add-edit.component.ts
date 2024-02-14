import { Component, Inject, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-employee-add-edit',
  templateUrl: './employee-add-edit.component.html',
  styleUrl: './employee-add-edit.component.css'
})

export class EmployeeAddEditComponent implements OnInit{

  private fb: FormBuilder = inject(FormBuilder)
  empForm!: FormGroup

  constructor(private employeeSvc: EmployeeService, private dialogRef: MatDialogRef<EmployeeAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.empForm = this.fb.group({
        firstName: ['', [Validators.required, Validators.minLength(3)]],
        lastName: ['', [Validators.required, Validators.minLength(2)]],
        email: ['', [Validators.required, Validators.email]],
        dob: ['', [Validators.required]],
        gender: ['', [Validators.required]],
        salary: ['', [Validators.required]],

      })
  }

  ngOnInit(): void {
    this.empForm.patchValue(this.data)
  }

  onSubmit(): void {
    if (this.empForm.valid) {
      if (this.data) {
        this.employeeSvc.updateEmployee(this.data.id, this.empForm.value)
        .subscribe({
          next: (val) => {
            alert("Record updated successfully")
            this.dialogRef.close()
          },
          error: (err) => {
            console.error("Error encountered while updating")
          }
        })
      } else {
        this.employeeSvc.addEmployee(this.empForm.value)
        .subscribe({
          next: (val) => {
            alert("Record created successfully")
            this.dialogRef.close()
          },
          error: (err) => {
            console.error("Error encountered while creating")
          }
        })
      }
    } 
  }
}
