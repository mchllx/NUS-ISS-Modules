import { outputAst } from '@angular/compiler';
import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { PictureData } from '../models';

@Component({
  selector: 'app-picture',
  templateUrl: './picture.component.html',
  styleUrl: './picture.component.css'
})
export class PictureComponent {

  @Input()
  caption: string="a picture"

  @Input(({required: true}))
  image: string= "https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png"

  imageStyle: string="thumbnail"

  @Output()
  imageEvent=new Subject<string>()

  imageClicked() {
    console.info(">>> image clicked: ", this.caption)
    this.imageEvent.next(this.caption)
  }

}
