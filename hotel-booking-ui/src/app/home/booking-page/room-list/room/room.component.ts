import { Component, EventEmitter, inject, Input, input, Output } from '@angular/core';
import { AvailableRooms } from '../../../../ds/room';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RoomService } from '../../../../service/room/room.service';

@Component({
  selector: 'app-room',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent {
  roomService = inject(RoomService);
  roomSig = this.roomService.room();
  @Input()
  room!: AvailableRooms;

  navigator:Router = inject(Router);
  showDetails = false;

  registerRoom() {
    this.roomService.room.set({id: 0, roomNumber: '', roomType: this.room.type, occupation: this.room.occupation, price: this.room.price});
    this.navigator.navigate(["booking/register-room"]);
  }
}
