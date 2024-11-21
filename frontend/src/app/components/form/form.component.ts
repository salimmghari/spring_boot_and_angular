import {Component, Input} from '@angular/core';
import IField from '../field/field';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  @Input() public title: string = '';
  @Input() public fields: IField[] = [];
  @Input() public link: string = '';
  @Input() public onLinkClick: (event: MouseEvent) => void = (event: MouseEvent) => {};
  @Input() public onClick: (event: MouseEvent) => void = (event: MouseEvent) => {};

  public onChange(change: (event: Event) => void): (event: Event) => void {
    return (event: Event): void => {
      change(event);
    }
  }
}
