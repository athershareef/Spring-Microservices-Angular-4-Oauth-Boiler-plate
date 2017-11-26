import { Component, OnInit } from '@angular/core';
import {logger} from 'codelyzer/util/logger';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    logger.info('w');
  }

}
