import { ChangeDetectionStrategy, Component, signal } from '@angular/core';
import { MatChipsModule } from '@angular/material/chips';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-hero-component',
  imports: [MatExpansionModule,
    MatChipsModule],
    changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './hero-component.html',
  styleUrl: './hero-component.scss',
})
export class HeroComponent {
readonly panelOpenState = signal(false);
}
