import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AvailableRooms, Room } from '../../../../ds/room';
import { CommonModule, ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-room-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './room-details.component.html',
  styleUrl: './room-details.component.css'
})
export class RoomDetailsComponent {
  @Input()
  room!: Room;

  @Output()
  detailsCloseEventEmitter = new EventEmitter();

  constructor(private viewportScroller: ViewportScroller) {
    this.viewportScroller.scrollToPosition([0, 0])
  }

  closeDetails() {
    this.detailsCloseEventEmitter.emit();
  }
}
