import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-search-bar-component',
  imports: [MatFormFieldModule, MatInputModule, MatIconModule, FormsModule],
  templateUrl: './search-bar-component.html',
  styleUrl: './search-bar-component.scss',
})
export class SearchBarComponent {
query: string = '';
  @Output() searchEvent = new EventEmitter<string>();

  emitSearch() {
    this.searchEvent.emit(this.query);
  }
}
