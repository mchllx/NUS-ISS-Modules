import { Component, Input } from '@angular/core';
import { PictureData } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  images: PictureData[] = [
    {
      url: "https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png",
      caption: 'Placeholder'
    },
    {
      url: "https://i.pinimg.com/564x/f7/1a/40/f71a40957de3e804c26c165376ac3498.jpg",
      caption: 'Pichu :D'
    },
    {
      url: "https://media.tenor.com/aRa7f2WPanUAAAAe/pokemon-piplup.png",
      caption: 'Piplup D:' 
    }
  ]

  pastCaptions: string[] = []

  imagePressed(caption: string): void {
    console.info('app.component: image pressed', caption)
    this.pastCaptions.push(caption)
  }

}

