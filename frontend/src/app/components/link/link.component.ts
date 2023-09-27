import {Component, Input} from '@angular/core';


@Component({
  selector: 'app-link',
  templateUrl: './link.component.html',
  styleUrls: ['./link.component.css'],
})
export class LinkComponent {
  @Input() public link: string = '';
  @Input() public onClick: (event: MouseEvent) => void = (event: MouseEvent) => {};

  public click(event: MouseEvent): void {
    this.onClick(event);
  }
}
