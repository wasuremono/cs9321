package hotel.chain;

public class RoomDTO {
	private int roomId;
	private int roomPrice;
	private String hotelLocation;
	private int roomType;
	private String[] roomTypeNames = {"Single", "Twin", "Queen", "Executive", "Suite"};
	
	
	public int getRoomId() {
		return roomId;
	}
	public int getRoomPrice() {
		return roomPrice;
	}
	public String getHotelLocation() {
		return hotelLocation;
	}
	public int getRoomType() {
		return roomType;
	}
	public String getRoomTypeName() {
		return roomTypeNames[roomType];
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	public void setHotelLocation(String location) {
		this.hotelLocation = location;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	
	
}
