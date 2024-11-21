import {Component, Input} from '@angular/core';


@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css'],
})
export class ButtonComponent {
  @Input() public className: string = '';
  @Input() public title: string = '';
  @Input() public onClick: (event: MouseEvent) => void = (event: MouseEvent) => {};

  public click(event: MouseEvent): void {
    this.onClick(event);
  }
}
