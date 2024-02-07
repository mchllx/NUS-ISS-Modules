import { Component, OnInit, Output, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Todo } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent implements OnInit {

  //newer way
  private fb: FormBuilder = inject(FormBuilder)
  // ctrl_comments = "comments"
  todoForm!: FormGroup

  taskArray!:FormArray

  @Output()
  newTodo = new Subject<Todo>()

  ngOnInit(): void {
    this.todoForm = this.createToDoForm()
  }

  // processForm() {
  //   const todo: Todo = this.todoForm.value
  //   console.log("process form", todo)
  // }

  processForm() {
    const todo = this.todoForm.value as Todo
    console.log("process form", todo)
    this.newTodo.next(todo)

    //reset() returns all of the arrays
    // this.todoForm.reset()
    this.todoForm = this.createToDoForm()
  }

  //create a group and push into array
  addTask() {
    this.taskArray.push(this.createTaskFrom())
  }

  deleteTask(i: number) {
    this.taskArray.removeAt(i)
  }

  //check if todo exists, no tasks shld be added if there arent todos
  isTodoInvalid(): boolean {
    return this.todoForm.invalid || this.taskArray.length <= 0
  }

  private createTaskFrom(): FormGroup {
    return this.fb.group({
      task: this.fb.control<string>("", [Validators.required, Validators.minLength(3)]),
      priority: this.fb.control<number>(1),
      completed: this.fb.control<boolean>(false)
    })

  }

  //this.taskArray = this.fbarray([], [Validators.required, Validators.minLength(1)])
  private createToDoForm(): FormGroup {
    this.taskArray = this.fb.array([])

    return this.fb.group({
      title: this.fb.control<string>("title", [Validators.required, Validators.minLength(3)]),
      email: this.fb.control<string>("email@gmail.com", [Validators.required, Validators.email]),
      completed: this.fb.control<string>("2024-02-14", [Validators.required, Validators.minLength(3)]),
      comments: this.fb.control<string>("comments"),
      tasks: this.taskArray
    })
  }
  
  //@Autowired
  // constructor(private fb:FormBuilder) { }

}
