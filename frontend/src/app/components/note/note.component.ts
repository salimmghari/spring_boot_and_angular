import {Component, Input} from '@angular/core';


@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent {
  @Input() public title: string = '';
  @Input() public body: string = '';
  @Input() public onTitleChange: (event: Event) => void = (event: Event) => {};
  @Input() public onBodyChange: (event: Event) => void = (event: Event) => {};
  @Input() public onCreate?: (event: MouseEvent) => void;
  @Input() public onUpdate?: (event: MouseEvent) => void;
  @Input() public onDelete?: (event: MouseEvent) => void;
}
