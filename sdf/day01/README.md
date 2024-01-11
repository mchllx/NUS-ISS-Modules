# day01 - Shopping Cart

<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/mchllx/day01">
    <img src="images/Logo.png" alt="Logo" height="40">
  </a>

<h3 align="center">Shopping Cart</h3>

  <p align="center">
    Console-based application in Java
    <br />
    <a href="https://github.com/mchllx/day01"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/mchllx/day01">View Demo</a>
    ·
    <a href="https://github.com/mchllx/day01/issues">Report Bug</a>
    ·
    <a href="https://github.com/mchllx/day01/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://github.com/mchllx/day01)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

<div align="left">
    <img src="images/Java-badge.png" alt="Java" >

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Version

* Version 3.0: Methods from Cart file instead of Main.java, Accepts strings for del()
* Version 2.0: Operations within the Main.java
* Version 1.0: Hardcoded with if statements and fixed values

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* Ensure that JDK21 is installed on your local computer

### Installation

1. Get a free copy at [https://www.java.com/en/download/](https://www.java.com/en/download/)
2. Clone the repo
   ```sh
   git clone https://github.com/mchllx/day01.git
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROGRAM STRUCTURE -->
## Program Structure
```
└── src/
    └── cart/
        └── Main.java
            ├── add(String)
            ├── del(String)
            ├── del(Integer) 
            ├── list()
            ├── help()   
            └── exit()
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

To use this java file, you may use these commands:
Compile
   ```
   javac -d classes src/*.java
   ```

Run
   ```
   java -cp classes Main
   ```  

Example
   ```
   java -cp classes cart.Main
   ```  

_For more examples, please refer to the [Documentation](https://github.com/mchllx/day01)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- FEATURES -->
## Features

### add(String)
<b>Class:</b> Main <br>
<b>Argument:</b> String <br>
<b>Returns:</b> void <br>
Takes in a String of one item and adds to cart

### del(Integer)
<b>Class:</b> Main <br>
<b>Argument:</b> Integer <br>
<b>Returns:</b> void <br>
Takes in an Integer of one item and removes a specified item from cart


### del(String)
<b>Class:</b> Main <br>
<b>Argument:</b> String <br>
<b>Returns:</b> void <br>
Takes in a String of one item and removes a specified item from cart


### list()
<b>Class:</b> Main <br>
<b>Argument:</b> - <br>
<b>Returns:</b> void <br>
Prints out an ordered list of elements in cart <br>
Error message: "Cart is empty" when there are no items in cart <br>

   ```
   1. apple
   2. orange
   3. pear
   4. grapes
   ```


### help()
<br>
<b>Class:</b> Main <br>
<b>Argument:</b> - <br>
<b>Returns:</b> void <br> 
Prints out a list of available commands: add, del, list, exit <br>


### exit()
<br>
<b>Class:</b> Main <br>
<b>Argument:</b> - <br>
<b>Returns:</b> void <br>
Exits cart <br>
Prints message: "Thank you for shopping with us" upon exit <br>

See the [open issues](https://github.com/mchllx/day01/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Project Link: [https://github.com/mchllx/day01](https://github.com/mchllx/day01)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/mchllx/day01.svg?style=for-the-badge
[contributors-url]: https://github.com/mchllx/day01/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/mchllx/day01.svg?style=for-the-badge
[forks-url]: https://github.com/mchllx/day01/network/members
[stars-shield]: https://img.shields.io/github/stars/mchllx/day01.svg?style=for-the-badge
[stars-url]: https://github.com/mchllx/day01/stargazers
[issues-shield]: https://img.shields.io/github/issues/mchllx/day01.svg?style=for-the-badge
[issues-url]: https://github.com/mchllx/day01/issues
[license-shield]: https://img.shields.io/github/license/mchllx/day01.svg?style=for-the-badge
[license-url]: https://github.com/mchllx/day01/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/mchllx
[product-screenshot]: images/Preview.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com
