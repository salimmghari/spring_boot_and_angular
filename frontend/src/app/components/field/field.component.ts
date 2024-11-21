import {Component, Input} from '@angular/core';


@Component({
    selector: 'app-field',
    templateUrl: './field.component.html',
    styleUrls: ['./field.component.css'],
})
export class FieldComponent {
  @Input() public label: string = '';
  @Input() public type: string = 'text';
  @Input() public placeholder: string = '';
  @Input() public defaultValue: string = '';
  @Input() public multiLine: boolean = false;
  @Input() public onChange: (event: Event) => void = (event: Event) => {};

  public change(event: Event): void {
    this.onChange(event);
  }
}
