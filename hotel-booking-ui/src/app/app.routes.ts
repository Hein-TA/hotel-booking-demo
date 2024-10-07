import { Routes } from '@angular/router';
import { RoomListComponent } from './home/booking-page/room-list/room-list.component';
import { SearchRoomComponent } from './home/booking-page/search-room/search-room.component';
import { RegisterRoomComponent } from './home/booking-page/register-room/register-room.component';
import { LandingPageComponent } from './home/landing-page/landing-page.component';
import { AccommodationComponent } from './home/landing-page/accommodation/accommodation.component';
import { BookingPageComponent } from './home/booking-page/booking-page.component';
import { FrontComponent } from './home/landing-page/front/front.component';
import { AccountInfomationComponent } from './home/account/account-infomation/account-infomation.component';
import { BookingHistoryComponent } from './home/account/booking-history/booking-history.component';
import { AccountComponent } from './home/account/account.component';
import { RestaurantComponent } from './home/landing-page/restaurant/restaurant.component';
import { AboutComponent } from './home/landing-page/about/about.component';

export const routes: Routes = [
    {
        path: "",
        component: LandingPageComponent,
        children: [
            {
                path: "",
                component: FrontComponent
            },
            {
                path: "accommodation",
                component: AccommodationComponent
            },
            {
                path: "restaurant",
                component: RestaurantComponent
            },
            {
                path: "about",
                component: AboutComponent
            },
        ]
    },
    {
        path: "booking",
        component: BookingPageComponent,
        children: [
            {
                path: "",
                redirectTo: "search-room",
                pathMatch: "full"
            },
            {
                path: "search-room",
                component: SearchRoomComponent
            },
            {
                path : "room-list",
                component: RoomListComponent,
            },
            {
                path: "register-room",
                component: RegisterRoomComponent
            }
        ],
    },
    {
        path: "account",
        component: AccountComponent,
        children: [
            {
                path: "info",
                component: AccountInfomationComponent
            },
            {
                path: "history",
                component: BookingHistoryComponent
            }
        ]
    }
];
