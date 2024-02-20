import { Component, OnInit } from '@angular/core';
import { Posts } from '../posts';
import { PostService } from '../post.service';

@Component({
  selector: 'app-postlist',
  templateUrl: './postlist.component.html',
  styleUrl: './postlist.component.css'
})

export class PostlistComponent implements OnInit{

  posts: Posts[] = []

  constructor(private postSvc: PostService) {
  }

  ngOnInit(): void {
    this.postSvc.getAllPosts().subscribe({
      next: (data: Posts[]) => {
        this.posts = data
      },
      error: (err: any) => {
        console.log(err) 
      }
    })
  }


}
